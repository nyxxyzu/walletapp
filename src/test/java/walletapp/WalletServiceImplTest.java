package walletapp;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import walletapp.wallet.Operation;
import walletapp.wallet.WalletService;
import walletapp.wallet.dto.NewWalletDto;
import walletapp.wallet.dto.OperationDto;
import walletapp.wallet.dto.WalletDto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;


@Transactional
@SpringBootTest
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WalletServiceImplTest {

	private final WalletService walletService;
	private WalletDto wallet = new WalletDto();

	@BeforeEach
	void createObjects() {
		wallet = walletService.create(new NewWalletDto("email@email.com", "username"));
	}

	@Test
	void testCreate() {
		assertThat(wallet.getId(), notNullValue());
		assertThat(wallet.getBalance(), equalTo(0L));
	}

	@Test
	void testOperation() {
		OperationDto dto = new OperationDto();
		dto.setOperation(Operation.DEPOSIT);
		dto.setAmount(1000L);
		dto.setId(wallet.getId());
		WalletDto changedWallet = walletService.makeOperation(dto);
		assertThat(changedWallet.getId(), equalTo(wallet.getId()));
		assertThat(changedWallet.getBalance(), equalTo(wallet.getBalance() + 1000));
	}

	@Test
	void testGetBalance() {
		WalletDto dto = walletService.getWalletBalance(wallet.getId());
		assertThat(dto, notNullValue());
		assertThat(wallet.getId(), equalTo(dto.getId()));
		assertThat(wallet.getBalance(), equalTo(dto.getBalance()));
	}
}
