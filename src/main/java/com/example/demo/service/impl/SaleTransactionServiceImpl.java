@Override
public List<SaleTransaction> getSalesByCampaign(Long campaignId) {
    // This override fixes the error in SaleTransactionServiceImpl
    return saleTransactionRepository.findByDiscountCode_Campaign_Id(campaignId);
}