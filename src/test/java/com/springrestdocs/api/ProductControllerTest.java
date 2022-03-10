package com.springrestdocs.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrestdocs.application.ProductResourceFixture;
import com.springrestdocs.application.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.springrestdocs.application.ProductResourceFixture.aProductResource;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;

@WebMvcTest(ProductController.class)
@ExtendWith(RestDocumentationExtension.class)
class ProductControllerTest {
    @MockBean
    ProductService productService;

    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void create() throws Exception {
        CreateProductRequest productRequest = CreateProductRequest.builder()
                .name("name")
                .description("descrption")
                .price(1000L)
                .build();

        mockMvc.perform(post("/api/v1/products")
                .header("RegisterId", "등록자")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productRequest)))
        .andDo(
                document("product/post/create",
                        requestHeaders(
                                headerWithName("RegisterId").description("등록자")
                        ),
                        requestFields(
                        fieldWithPath("name").type(JsonFieldType.STRING).description("상품명")
                                .attributes(key("constraints")
                                        .value("상품명은 필수 값 입니다.")),
                        fieldWithPath("description").type(JsonFieldType.STRING).description("상품 설명")
                                .attributes(key("constraints")
                                        .value("상품 설명은 필수 값 입니다.")),
                        fieldWithPath("price").type(JsonFieldType.NUMBER).description("가격")
                                .attributes(key("constraints")
                                        .value("가격은 0원 이상 입력해주세요."))
                ))
        );
    }

    @Test
    void getProduct() throws Exception {
        // given
        when(productService.getProduct(1L))
        .thenReturn(aProductResource().build());

        // when
        mockMvc.perform(get("/api/v1/products/{id}", 1L))
        .andDo(
                document("product/get/byId",
                        pathParameters(
                                parameterWithName("id").description("상품 고유 번호")
                        ),
                        responseFields(
                                fieldWithPath("id").type(JsonFieldType.NUMBER).description("상품 고유 번호"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("상품명"),
                                fieldWithPath("description").type(JsonFieldType.STRING).description("상품 설명"),
                                fieldWithPath("price").type(JsonFieldType.NUMBER).description("가격")
                        )
                )
        );
    }

    @Test
    void getProducts() throws Exception {
        // given
        when(productService.getProducts(any()))
        .thenReturn(ProductResourceFixture.aList());

        // when
        mockMvc.perform(get("/api/v1/products")
                        .param("page", "0")
                        .param("size", "10"))
        .andDo(
            document("product/get/all",
                    requestParameters(
                            parameterWithName("page").description("페이지 번호"),
                            parameterWithName("size").description("보여줄 갯수")
                    ),
                    responseFields(
                            fieldWithPath("[].id").type(JsonFieldType.NUMBER).description("상품 고유 번호"),
                            fieldWithPath("[].name").type(JsonFieldType.STRING).description("상품명"),
                            fieldWithPath("[].description").type(JsonFieldType.STRING).description("상품 설명"),
                            fieldWithPath("[].price").type(JsonFieldType.NUMBER).description("가격")
                    )
                )
        );
    }
}
