package walletapp.wallet;

import walletapp.wallet.dto.NewWalletDto;
import walletapp.wallet.dto.OperationDto;
import walletapp.wallet.dto.WalletDto;

public interface WalletService {
	WalletDto create(NewWalletDto dto);

	WalletDto makeOperation(OperationDto dto);

	WalletDto getWalletBalance(long walletId);
}
