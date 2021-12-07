package com.werth.getethbalance.model;

import java.util.ArrayList;

public class BlockChain {
    private Block genesisBlock;
    private ArrayList<Block> blockChain;

    public BlockChain() {
        this.blockChain = new ArrayList<>();
        this.genesisBlock = new Block("0");
        blockChain.add(genesisBlock);
    }

    public Block getLastBlock() {
        if(blockChain.size() > 1) {
            return blockChain.get(blockChain.size() -1);
        }
        return genesisBlock;
    }

    public Block getGenesisBlock() {
        return genesisBlock;
    }

    public ArrayList<Block> getBlockChain() {
        return blockChain;
    }

    @Override
    public String toString() {
        return "BlockChain{" +
                "genesisBlock=" + genesisBlock +
                ", blockChain=" + blockChain +
                '}';
    }
}
