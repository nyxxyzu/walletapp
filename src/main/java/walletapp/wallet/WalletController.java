package walletapp.wallet;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import walletapp.wallet.dto.NewWalletDto;
import walletapp.wallet.dto.OperationDto;
import walletapp.wallet.dto.WalletDto;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class WalletController {

	private final WalletService walletService;

	@Autowired
	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}

	@PostMapping("/wallets")
	@ResponseStatus(HttpStatus.CREATED)
	public WalletDto create(@Valid @RequestBody NewWalletDto dto) {
		WalletDto createdWallet = walletService.create(dto);
		log.info("Created wallet {}", createdWallet.toString());
		return createdWallet;
	}

	@PostMapping("/wallet")
	public WalletDto makeOperation(@Valid @RequestBody OperationDto dto) {
		WalletDto wallet = walletService.makeOperation(dto);
		log.info("Made a {} operation on the wallet {} for {}", dto.getOperation(), dto.getId(), dto.getAmount());
		return wallet;
	}

	@GetMapping("/wallets/{walletId}")
	public WalletDto getBalance(@PathVariable("walletId") Long walletId) {
		return walletService.getWalletBalance(walletId);
	}

}
