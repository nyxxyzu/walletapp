package walletapp.wallet.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WalletDto {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	private Long balance;
}
