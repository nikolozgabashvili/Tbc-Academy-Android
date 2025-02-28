package com.example.tbcacademyhomework.domain.usecases

import com.example.tbcacademyhomework.domain.model.LocationDomain
import com.example.tbcacademyhomework.domain.repository.LocationRepository
import com.example.tbcacademyhomework.domain.util.DataError
import com.example.tbcacademyhomework.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val locationRepository: LocationRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<LocationDomain>, DataError>> {
        return locationRepository.getLocations()
    }


}