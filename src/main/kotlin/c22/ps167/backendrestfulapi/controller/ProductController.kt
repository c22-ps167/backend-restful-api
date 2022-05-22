package c22.ps167.backendrestfulapi.controller

import c22.ps167.backendrestfulapi.data.model.CreateProductRequest
import c22.ps167.backendrestfulapi.data.model.WebResponse
import c22.ps167.backendrestfulapi.data.model.dto.ProductDto
import c22.ps167.backendrestfulapi.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(private val productService: ProductService) {

    @PostMapping(
        value = ["/api/p"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductDto> {
        val response = productService.create(body)
        return WebResponse(
            code = 200,
            status = "success",
            data = response
        )
    }

    @PostMapping(
        value = ["/api/p/"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createBulkProduct(@RequestBody body: List<CreateProductRequest>): WebResponse<String> {
        val response = productService.createBulk(body)
        return WebResponse(
            code = 200,
            status = "success",
            data = "added $response products"
        )
    }

    @GetMapping(
        value = ["/api/p/{id}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id") id: String): WebResponse<ProductDto> {
        val response = productService.get(id)
        return WebResponse(
            code = 200,
            status = "success",
            data = response
        )
    }
}