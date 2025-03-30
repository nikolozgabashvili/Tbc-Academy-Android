package com.example.tbcacademyhomework.data.mapper

import com.example.tbcacademyhomework.data.model.account.ExchangeCourseDTO
import com.example.tbcacademyhomework.domain.model.exchange.ExchangeCourseDomain

fun ExchangeCourseDTO.toDomain(): ExchangeCourseDomain {
    return ExchangeCourseDomain(course = course)
}