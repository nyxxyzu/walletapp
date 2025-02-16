package walletapp.wallet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import walletapp.exceptions.NotFoundException;
import walletapp.exceptions.ValidationException;
import walletapp.wallet.dto.NewWalletDto;
import walletapp.wallet.dto.OperationDto;
import walletapp.wallet.dto.WalletDto;
import walletapp.wallet.dto.WalletMapper;

@Service
@Transactional(readOnly = true)
public class WalletServiceImpl implements WalletService {

	private final WalletRepository walletRepository;

	@Autowired
	public WalletServiceImpl(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;

	}

	@Override
	@Transactional
	public WalletDto create(NewWalletDto dto) {
		Wallet wallet = WalletMapper.toWallet(dto);
		wallet.setBalance(0L);
		return WalletMapper.toWalletDto(walletRepository.save(wallet));
	}

	@Override
	@Transactional
	public WalletDto makeOperation(OperationDto dto) {
		Wallet wallet = walletRepository.findById(dto.getId())
				.orElseThrow(() -> new NotFoundException("Wallet not found"));
		if (dto.getOperation() == Operation.DEPOSIT) {
			wallet.setBalance(wallet.getBalance() + dto.getAmount());
		}
		if (dto.getOperation() == Operation.WITHDRAW) {
			if (dto.getAmount() > wallet.getBalance()) {
				throw new ValidationException("Insufficient funds");
			}
			wallet.setBalance(wallet.getBalance() - dto.getAmount());
		}
		return WalletMapper.toWalletDto(wallet);
	}

	@Override
	public WalletDto getWalletBalance(long walletId) {
		Wallet wallet = walletRepository.findById(walletId)
				.orElseThrow(() -> new NotFoundException("Wallet not found"));
		return WalletMapper.toWalletDto(wallet);
	}
}
