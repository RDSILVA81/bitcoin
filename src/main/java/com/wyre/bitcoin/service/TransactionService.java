package com.wyre.bitcoin.service;

import com.wyre.bitcoin.listener.PaymentReceiverListener;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.wallet.KeyChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {

    private final WalletAppKit walletAppKit;

    @PostConstruct
    public void postConstructor(){
        walletAppKit.startAsync();
        //log.info("************************************  NetID :" + walletAppKit.wallet().getNetworkParameters().getId());
        walletAppKit.awaitRunning();
    }

//    public String createPaymentAddress(){
//        return walletAppKit.wallet().freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS).toString();
//    }

    public void addListener(PaymentReceiverListener receiver){
        walletAppKit.wallet().addCoinsReceivedEventListener(receiver);
    }


}
