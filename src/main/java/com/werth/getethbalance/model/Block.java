package com.werth.getethbalance.model;

import jnr.ffi.annotations.In;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;

// Each block should represent all of the transactions which take place

public class Block {
    private String hash;
    private String prevHash;
    private ArrayList<Transaction> transactions;
    private int nonce;

    public Block(String prevHash) {
        this.transactions = new ArrayList<>();
        this.prevHash = prevHash;
        this.hash = calculateBlockHash();
    }

    // First, we concatenate different parts of the block to generate a hash from
    // Then, we get an instance of the SHA-256 hash function from MessageDigest
    // Then, we generate the hash value of our input data, which is a byte array
    // Finally, we transform the byte array into a hex string, a hash is typically represented as a 32-digit hex number

    private String calculateBlockHash() {
        String hashData = prevHash + Integer.toString(nonce) + transactions.hashCode();
        MessageDigest messageDigest = null;
        byte[] bytes = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            bytes = messageDigest.digest(hashData.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        for(byte b: bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }

    // We start by defining the prefix we desire to find
    // Then we check whether we've found the solution
    // If not we increment the nonce and calculate the hash in a loop
    // The loop goes on until we hit the jackpot

    public String mineBlock(int prefix) {
        String prefixString = new String(new char[prefix]).replace('\0', '\0');
        while(!hash.substring(0, prefix).equals(prefixString)) {
            nonce++;
            hash = calculateBlockHash();
        }
        return hash;
    }

    public void addTransaction(Transaction transaction) {
        try {
            this.transactions.add(transaction);
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public String getHash() {
        return hash;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public int getNonce() {
        return nonce;
    }

    @Override
    public String toString() {
        return "Block{" +
                "hash=" + hash +
                ", prevHash=" + prevHash +
                ", transactions=" + transactions.toString() +
                '}';
    }
}
