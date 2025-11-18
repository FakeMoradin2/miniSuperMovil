package com.example.project_miniMart.domain.repositories

import com.example.project_miniMart.datasource.common.ResponseStatus
import com.example.project_miniMart.datasource.network.data.FakeContract
import com.example.project_miniMart.domain.mappers.ProductsDtoToMapper.Companion.fromDtoToDomainList
import com.example.project_miniMart.domain.models.ProductDomain
import javax.inject.Inject

/**
 * Interfaz que define las operaciones para la pantalla de inicio.
 */
interface HomeTask {
    /**
     * Obtiene todos los productos.
     *
     * @return El estado de la respuesta con la lista de productos.
     */
    suspend fun fetchAllProducts(): ResponseStatus<List<ProductDomain>>
}

/**
 * Implementación de [HomeTask] que utiliza un contrato falso para obtener los datos.
 *
 * @param fakeContract El contrato de datos falsos.
 */
class HomeRepository @Inject constructor(private val fakeContract: FakeContract) : HomeTask {

    override suspend fun fetchAllProducts(): ResponseStatus<List<ProductDomain>> {
        val response = fakeContract.fetchAllProducts()
        return ResponseStatus.Success(fromDtoToDomainList(response.data))
    }
}
