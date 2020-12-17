package com.wyre.bitcoin.service;

import com.wyre.bitcoin.listener.PaymentReceiverListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.wallet.KeyChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_= @Autowired)
public class TransactionService {

    private final WalletAppKit walletAppKit;

    @PostConstruct
    public void postConstructor() throws InterruptedException {
        walletAppKit.startAsync();
        walletAppKit.awaitRunning();
        String myAddress = walletAppKit.wallet().freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS).toString();
        log.info("My Address : " + myAddress);
    }

    public void addListener(PaymentReceiverListener receiver){
        walletAppKit.wallet().addCoinsReceivedEventListener(receiver);
    }


}
