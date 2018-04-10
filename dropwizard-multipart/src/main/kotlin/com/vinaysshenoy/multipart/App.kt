package com.vinaysshenoy.multipart

import io.dropwizard.Application
import io.dropwizard.client.JerseyClientBuilder
import io.dropwizard.setup.Environment
import org.glassfish.jersey.media.multipart.MultiPartFeature

fun main(args: Array<String>) {
	App().run(*args)
}

class App : Application<Config>() {

	override fun run(
			configuration: Config,
			environment: Environment
	) {
		val jerseyClient = JerseyClientBuilder(environment)
				.build("remote")

		jerseyClient.register(MultiPartFeature::class.java)

		environment.jersey()
				.register(TestResource(jerseyClient))
	}

}