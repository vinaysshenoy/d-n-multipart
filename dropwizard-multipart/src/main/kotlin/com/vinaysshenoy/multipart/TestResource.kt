package com.vinaysshenoy.multipart

import org.glassfish.jersey.media.multipart.MultiPart
import org.slf4j.LoggerFactory
import java.io.File
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.client.Client
import javax.ws.rs.core.Response

@Path("/")
class TestResource(private val client: Client) {

	private val logger = LoggerFactory.getLogger("TestResource")

	private val remoteGetTarget = client.target("http://localhost:3000")

	@GET
	fun get(): Response {

		val response = remoteGetTarget.request()
				.get()

		try {
			val multiPart = response.readEntity(MultiPart::class.java)

			multiPart.bodyParts.forEachIndexed { index, part ->
				logger.info("--------")
				logger.info("$index")
				logger.info("Type: ${part.contentDisposition.type}")
				logger.info("File Name: ${part.contentDisposition.fileName}")
				logger.info("Name: ${part.contentDisposition.parameters["name"]}")

				val name = part.contentDisposition.parameters["name"]!!
				when (name) {
					"data1" -> {
						val model = part.getEntityAs(Model1::class.java)
						logger.info("Data 1: $model")
					}
					"data2" -> {
						val model = part.getEntityAs(Model2::class.java)
						logger.info("Data 2: $model")
					}
					"image" -> {
						val file = part.getEntityAs(File::class.java)
						logger.info("File: ${file.absolutePath}, ${file.length()}")
					}
				}
				logger.info("--------")
			}
		} catch (cause: Throwable) {
			logger.error("Error reading multipart", cause)
		}

		return Response.ok()
				.build()
	}
}