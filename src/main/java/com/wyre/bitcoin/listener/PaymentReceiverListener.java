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

    private static final double BITCOIN_VAL = 0.00000001;

    private final PaymentRepo repo;

    @PostConstruct
    public void postConstruct(){
        service.addListener(this);
    }

    @Override
    public void onCoinsReceived(Wallet wallet, Transaction transaction, Coin coin, Coin coin1) {
            Payment pay = new Payment();
            pay.setPayFrom(LegacyAddress.fromKey(wallet.getParams(),wallet.currentReceiveKey()).toString());
            pay.setPayDate(new Date());
            pay.setValue(BITCOIN_VAL * transaction.getValueSentToMe(wallet).getValue());
            repo.save(pay);
    }
}
