package com.wyre.bitcoin.listener;

import com.wyre.bitcoin.domain.Payment;
import com.wyre.bitcoin.repository.PaymentRepo;
import com.wyre.bitcoin.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.LegacyAddress;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor_= @Autowired)
public class PaymentReceiverListener implements WalletCoinsReceivedEventListener {

    private final TransactionService service;

    private PaymentRepo repo;

    @PostConstruct
    public void postConstruct(){
        service.addListener(this);
    }

    @Override
    public void onCoinsReceived(Wallet wallet, Transaction transaction, Coin coin, Coin coin1) {
        transaction.getOutputs().forEach(trx ->{

            Coin val = transaction.getValueSentToMe(wallet);
            Coin val2 = trx.getValue();
            log.info("******************************************************************");
            log.info("******************************************************************");
            log.info("******************************************************************");
            log.info("trx.getValue().toFriendlyString() : " + trx.getValue().toFriendlyString());
            log.info("LegacyAddress.fromKey(wallet.getParams(),wallet.currentReceiveKey()): " + LegacyAddress.fromKey(wallet.getParams(),wallet.currentReceiveKey()));
            log.info("trx.getScriptPubKey().toString(): "+ trx.getScriptPubKey().toString());
            log.info("trx.getValue: "+ trx.getValue());
            log.info("val: "+ val.getValue());
            log.info("val-2: "+ val2.getValue());
            log.info("coin: "+ coin.getValue());
            log.info("******************************************************************");
            log.info("******************************************************************");
            log.info("******************************************************************");
        });
    }
}
