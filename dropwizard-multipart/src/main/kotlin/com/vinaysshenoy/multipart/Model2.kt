package com.vinaysshenoy.multipart

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Model2 @JsonCreator constructor(
		@get:JsonProperty("val1")
		@param:JsonProperty("val1")
		val val1: String
)