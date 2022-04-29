package com.example.demo;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class ApiController {
    
    @GetMapping(value="test")
    public Object getMethodName() {
        return new Foo();
    }
    
    @JsonComponent
    public class Foo extends Object {
        public String getName() {
            return "test";
        }
        public int getAge() {
            return 123;
        }
        public static class Serializer extends JsonObjectSerializer<Foo> {

            @Override
            protected void serializeObject(Foo value, JsonGenerator jgen, SerializerProvider provider)
                    throws IOException {
                jgen.writeStringField("name", value.getName());
                jgen.writeNumberField("age", value.getAge());
            }
    
        }
    }
}
