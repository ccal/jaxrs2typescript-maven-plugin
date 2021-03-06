/*******************************************************************************
 * Copyright 2013 Raphael Jolivet
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.bluetrainsoftware.maven.jaxrs2typescript;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;

public class DescriptorGeneratorTest {

	private Writer out;

	static class MyObject {
		public String field;
	}

	@Path("/")
	static private interface ExampleService {

		@Path("/{id}")
		@POST
		public String aPostMethod(//
                              @QueryParam("q1") String queryParam, //
                              @PathParam("id") String id, //
                              @FormParam("formParam") Integer formParam, //
                              String postPayload);

		@Path("/{id}")
		@GET
		public void aGetMethod(//
                           @QueryParam("q1") String queryParam, //
                           @PathParam("id") String id, //
                           @FormParam("formParam") Integer formParam, //
                           MyObject postPayload);

	}

	@Before
	public void setUp() {
		out = new OutputStreamWriter(System.out);
	}


  @Test
  public void testExampleService() {
    new ServiceDescriptorGenerator(Arrays.asList(ExampleService.class, AnotherService.class, IdentityService.class),
      out, "http://localhost:8080");
  }
}
