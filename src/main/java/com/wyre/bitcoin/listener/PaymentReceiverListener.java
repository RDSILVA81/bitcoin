package com.wyre.bitcoin.listener;

import com.wyre.bitcoin.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentReceiverListener implements WalletCoinsReceivedEventListener {

    private final TransactionService service;

    @PostConstruct
    public void postConstruct(){
        service.addListener(this);
    }

    @Override
    public void onCoinsReceived(Wallet wallet, Transaction transaction, Coin coin, Coin coin1) {
        transaction.getOutputs().forEach(trx ->{
            log.info(trx.getValue().toFriendlyString());
            log.info(trx.getScriptPubKey().toString());
        });
    }
}
