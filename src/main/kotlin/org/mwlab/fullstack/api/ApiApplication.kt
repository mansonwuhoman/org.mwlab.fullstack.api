package org.mwlab.fullstack.api

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource
import org.glassfish.jersey.server.ResourceConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}

@Component
@Path("/chat")
class ChatMessageResource{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    fun getMessage( @PathParam("userId") fromUserId: String ): List<Message> =
            listOf(
                    Message(
                            fromUserId  = fromUserId,
                            toUserId    = "Jacky",
                            message     = "Hello"
                    )
            )
}

data class Message(
        val fromUserId: String,
        val toUserId:   String,
        val message:    String,
        val dateTime:   LocalDateTime = LocalDateTime.now()
)

@Component
class ChatMessageResourceConfig : ResourceConfig(){
    init {
        registerEndpoints()
    }

    private fun registerEndpoints() {
        register(ChatMessageResource())
        register(OpenApiResource())
    }
}