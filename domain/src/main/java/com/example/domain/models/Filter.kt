package com.example.domain.models

import java.time.LocalDate


data class Filter(
    var dateFrom: LocalDate? = null,
    var dateTo: LocalDate = LocalDate.now(),
    var searchIn: SearchIn = SearchIn.All,
)