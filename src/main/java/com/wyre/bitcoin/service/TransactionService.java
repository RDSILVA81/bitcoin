package com.wyre.bitcoin.service;

import com.google.common.collect.ImmutableList;
import com.wyre.bitcoin.domain.Payment;
import com.wyre.bitcoin.listener.PaymentReceiverListener;
import com.wyre.bitcoin.repository.PaymentRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.wallet.KeyChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_= @Autowired)
public class TransactionService {

    private final WalletAppKit walletAppKit;

    private final PaymentRepo repo;

    private String myAddress;

    @PostConstruct
    public void postConstructor() throws InterruptedException {
        walletAppKit.startAsync();
        walletAppKit.awaitRunning();
        myAddress = walletAppKit.wallet().freshAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS).toString();
        log.info("My Address : " + myAddress);
    }

    public void addListener(PaymentReceiverListener receiver){
        walletAppKit.wallet().addCoinsReceivedEventListener(receiver);
    }

    public List<Payment> getMyPayments(){
        return ImmutableList.copyOf(repo.findAll());
    }

    public Double getSumPayments(){
        return ImmutableList.copyOf(repo.findAll()).stream().mapToDouble(payment -> payment.getValue()).sum();
    }

    public String getMyAddress(){
        return myAddress;
    }

}
