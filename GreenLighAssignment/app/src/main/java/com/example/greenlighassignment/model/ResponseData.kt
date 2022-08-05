package com.example.greenlighassignment.model

data class ResponseData(
    val sales_area: ArrayList<SalesArea>,
    val sales_country: ArrayList<SalesCountry>,
    val sales_region: ArrayList<SalesRegion>,
    val sales_zone: ArrayList<SalesZone>
)