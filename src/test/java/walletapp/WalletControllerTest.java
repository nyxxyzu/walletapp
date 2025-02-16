package walletapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import walletapp.wallet.Operation;
import walletapp.wallet.WalletController;
import walletapp.wallet.WalletService;
import walletapp.wallet.dto.NewWalletDto;
import walletapp.wallet.dto.OperationDto;
import walletapp.wallet.dto.WalletDto;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = WalletController.class)
public class WalletControllerTest {

	@Autowired
	ObjectMapper mapper;

	@MockitoBean
	WalletService walletService;

	@Autowired
	private MockMvc mvc;

	private WalletDto walletDto = new WalletDto();
	private NewWalletDto newWalletDto = new NewWalletDto();
	private OperationDto operationDto = new OperationDto();

	@BeforeEach
	void makeWalletDto() {
		walletDto.setId(1L);
		walletDto.setBalance(0L);
		newWalletDto.setEmail("email@email.com");
		newWalletDto.setUsername("username");
		operationDto.setOperation(Operation.DEPOSIT);
		operationDto.setId(1L);
		operationDto.setAmount(1000L);
	}

	@Test
	void createWalletTest() throws Exception {
		when(walletService.create(any()))
				.thenReturn(walletDto);

		mvc.perform(post("/api/v1/wallets")
				.content(mapper.writeValueAsString(newWalletDto))
				.characterEncoding(StandardCharsets.UTF_8)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(walletDto.getId()), Long.class))
				.andExpect(jsonPath("$.balance", is(walletDto.getBalance()), Long.class));
	}

	@Test
	void operationTest() throws Exception {
		when(walletService.makeOperation(any()))
				.thenReturn(walletDto);

		mvc.perform(post("/api/v1/wallet")
						.content(mapper.writeValueAsString(operationDto))
						.characterEncoding(StandardCharsets.UTF_8)
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(walletDto.getId()), Long.class))
				.andExpect(jsonPath("$.balance", is(walletDto.getBalance()), Long.class));
	}

	@Test
	void getBalanceTest() throws Exception {
		when(walletService.getWalletBalance(anyLong()))
				.thenReturn(walletDto);

		mvc.perform(get("/api/v1/wallets/" + walletDto.getId())
				.characterEncoding(StandardCharsets.UTF_8)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(walletDto.getId()), Long.class))
				.andExpect(jsonPath("$.balance", is(walletDto.getBalance()), Long.class));
	}
}
