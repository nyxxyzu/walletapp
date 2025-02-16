package walletapp.wallet.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import walletapp.wallet.Operation;

@Data
public class OperationDto {

	@NotNull
	private Long id;
	@NotNull
	private Operation operation;
	@NotNull
	@Positive
	private Long amount;
}
