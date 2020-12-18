package com.wyre.bitcoin.configuration;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.TestNet3Params;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;

@Configuration
public class BitCoinConfiguration {

    @Value("${bitcoin-network}")
    private String network;

    @Value("${bitcoin-folder}")
    private String folder;

    @Value("${bitcoin-prefix-file}")
    private String prefixFile;

    @Bean
    public NetworkParameters networkParameters(){
        if(NetworkParameters.ID_TESTNET.equals(network)){
            return TestNet3Params.get();
        }else{
            return MainNetParams.get();
        }
    }

    @Bean
    public WalletAppKit walletAppKit(NetworkParameters netWork){
        return new WalletAppKit(netWork,new File(folder),prefixFile);
    }

}
