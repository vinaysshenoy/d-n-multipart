package com.vinaysshenoy.multipart

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Model1 @JsonCreator constructor(

		@get:JsonProperty("val1")
		@param:JsonProperty("val1")
		val val1: Int,

		@get:JsonProperty("val2")
		@param:JsonProperty("val2")
		val val2: String,

		@get:JsonProperty("val3")
		@param:JsonProperty("val3")
		val val3: List<String>
)