package guru.springframework.api.v1.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by tairovich_jr on Oct 17, 2020
 */
@Data
@AllArgsConstructor
public class VendorListDTO {

	private List<VendorDTO> vendors;
}
