package walletapp.wallet.dto;

import lombok.experimental.UtilityClass;
import walletapp.wallet.Wallet;

@UtilityClass
public class WalletMapper {

	public static Wallet toWallet(NewWalletDto dto) {
		Wallet wallet = new Wallet();
		wallet.setEmail(dto.getEmail());
		wallet.setUsername(dto.getUsername());
		return wallet;
	}

	public static WalletDto toWalletDto(Wallet wallet) {
		WalletDto dto = new WalletDto();
		dto.setId(wallet.getId());
		dto.setBalance(wallet.getBalance());
		return dto;
	}
}
