package com.yc.springboot.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@SpringBootTest
class AccountTest {

    /**
     * 初始金额
     */
    private static final int ORIGINAL_BALANCE = 10000;

    private Transactions transactions;

    private Account account;

    @BeforeEach
    void setUp() {
        // 创建被测对象
        account = new Account();
        // 用Mockito创建测试替身
        transactions = mock(Transactions.class);
        // 注入测试替身
        account.setTransactions(transactions);
        // 调用存款方法，设置冻结状态
        account.deposit(ORIGINAL_BALANCE);
    }

    /**
     * 账户状态正常，取款金额小于当前余额时取款成功
     */
    @Test
    void shouldSuccess() {
        int amountOfWithdraw = 2000;
        // 调用测试方法，执行测试
        account.withdraw(amountOfWithdraw);
        // 断言当前余额等于原有余额 - 取款金额
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE - amountOfWithdraw);
        // 断言调用了外部依赖transactions的add方法，以account, TransactionType.CREDIT, amountOfWithdraw为参数
        verify(transactions).add(account, TransactionType.CREDIT, amountOfWithdraw);
    }

    /**
     * 将余额全部取完，也可以取款成功
     */
    @Test
    void shouldSuccessWhileWithdrawAll() {
        int amountOfWithdraw = ORIGINAL_BALANCE;
        account.withdraw(amountOfWithdraw);
        assertThat(account.getBalance()).isEqualTo(0);
        verify(transactions).add(account, TransactionType.CREDIT, amountOfWithdraw);
    }

    /**
     * 账户被冻结，取款应当失败，且未调用存取记录
     */
    @Test
    void shouldFailWhileAccountLocked() {
        account.lock();
        assertThrows(AccountLockedException.class, () -> {
            account.withdraw(2000);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, 2000);
    }

    /**
     * 取款金额为负数，取款应该失败
     */
    @Test
    void shouldFailWhileAmountLessThanZero() {
        assertThrows(InvalidAmountException.class, () -> {
            account.withdraw(-1);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, -1);
    }

    /**
     * 取款金额是0，应当失败
     */
    @Test
    void shouldFailWhileAmountEqualToZero() {
        assertThrows(InvalidAmountException.class, () -> {
            account.withdraw(0);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, 0);
    }

    /**
     * 余额不足，应当失败
     */
    @Test
    void shouldFailWhileBalanceInsufficient() {
        int amountOfWithdraw = ORIGINAL_BALANCE + 1;
        assertThrows(BalanceInsufficientException.class, () -> {
           account.withdraw(amountOfWithdraw);
        });
        assertThat(account.getBalance()).isEqualTo(ORIGINAL_BALANCE);
        verify(transactions, never()).add(account, TransactionType.CREDIT, amountOfWithdraw);

    }
}