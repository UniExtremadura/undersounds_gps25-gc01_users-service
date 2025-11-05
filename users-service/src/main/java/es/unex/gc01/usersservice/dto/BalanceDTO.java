package es.unex.gc01.usersservice.dto;


public class BalanceDTO {
    private Long id;
    private Long artistId;
    private Double monthlyBalance;
    private String balanceDate;
    private String status;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
    public Double getMonthlyBalance() { return monthlyBalance; }
    public void setMonthlyBalance(Double monthlyBalance) { this.monthlyBalance = monthlyBalance; }
    public String getBalanceDate() { return balanceDate; }
    public void setBalanceDate(String balanceDate) { this.balanceDate = balanceDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}