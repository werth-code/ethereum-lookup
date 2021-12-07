package com.werth.getethbalance;

import com.werth.getethbalance.model.Block;
import com.werth.getethbalance.model.BlockChain;
import com.werth.getethbalance.model.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.ArrayList;
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

//		System.out.println("BigInteger Wallet Value: " + wei);

		BlockChain blockChain = new BlockChain();
		ArrayList<Block> blocks = blockChain.getBlockChain();

		Block block1 = new Block(blockChain.getLastBlock().getHash());
		block1.addTransaction(new Transaction("Transaction 1", wei));
		blocks.add(block1);

		Block block2 = new Block(blockChain.getLastBlock().getHash());
		block2.addTransaction(new Transaction("Transaction 2", new BigInteger("300")));
		blocks.add(block2);

		System.out.println(block1);

		System.out.println(block2);

	}

}
