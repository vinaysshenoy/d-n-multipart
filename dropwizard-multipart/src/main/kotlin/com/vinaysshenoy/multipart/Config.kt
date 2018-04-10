package com.vinaysshenoy.multipart

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.client.JerseyClientConfiguration
import javax.validation.Valid
import javax.validation.constraints.NotNull

class Config: Configuration() {

	@Valid
	@NotNull
	@JsonProperty("jerseyClient")
	var jerseyClientConfiguration = JerseyClientConfiguration()
}