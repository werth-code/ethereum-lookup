package com.werth.getethbalance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class GetEthBalanceApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(GetEthBalanceApplication.class, args);

		Web3j web3 = Web3j.build(new HttpService("https://mainnet.infura.io/v3/7665ceee9a454a97bb16175d78dbb0e8"));

		EthGetBalance ethGetBalance = web3
				.ethGetBalance("0x9c5c1a5be23ae128f0b2cbd4dfb95774321e3d5c", DefaultBlockParameterName.LATEST)
				.sendAsync()
				.get();

		BigInteger wei = ethGetBalance.getBalance();

		System.out.println(wei);
	}

}
