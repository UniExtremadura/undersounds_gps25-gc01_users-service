package es.unex.gc01.usersservice.dto;


import java.util.List;

public class ArtistPaymentInfoDTO {
    private String username;
    private Double totalBalance;
    private Double thisMonth;
    private String iban;
    private String accountPropietary;
    private List<BalanceDTO> balance;
    private List<PaymentDTO> status;

    // Getters y Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public Double getTotalBalance() { return totalBalance; }
    public void setTotalBalance(Double totalBalance) { this.totalBalance = totalBalance; }
    public Double getThisMonth() { return thisMonth; }
    public void setThisMonth(Double thisMonth) { this.thisMonth = thisMonth; }
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
    public String getAccountPropietary() { return accountPropietary; }
    public void setAccountPropietary(String accountPropietary) { this.accountPropietary = accountPropietary; }
    public List<BalanceDTO> getBalance() { return balance; }
    public void setBalance(List<BalanceDTO> balance) { this.balance = balance; }
    public List<PaymentDTO> getStatus() { return status; }
    public void setStatus(List<PaymentDTO> status) { this.status = status; }
}