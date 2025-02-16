package walletapp.wallet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewWalletDto {

	@NotBlank
	@Email
	@Size(min = 6, max = 256)
	private String email;
	@NotBlank
	@Size(min = 2, max = 250)
	private String username;
}
