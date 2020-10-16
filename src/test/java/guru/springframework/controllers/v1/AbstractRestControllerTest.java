package guru.springframework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by tairovich_jr on Oct 16, 2020
 */
public class AbstractRestControllerTest {

	   public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
}
