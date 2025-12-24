@Service
public class RoiServiceImpl implements RoiService {
    private final RoiReportRepository roiRepository;
    private final CampaignRepository campaignRepository;
    private final SaleTransactionRepository saleRepository;

    // Constructor Injection (Requirement)
    public RoiServiceImpl(RoiReportRepository roiRepository, 
                          CampaignRepository campaignRepository, 
                          SaleTransactionRepository saleRepository) {
        this.roiRepository = roiRepository;
        this.campaignRepository = campaignRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public RoiReport generateCampaignRoi(Long campaignId) {
        Campaign campaign = campaignRepository.findById(campaignId)
            .orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));

        // Aggregate sales for all codes in this campaign
        BigDecimal totalRevenue = saleRepository.findByDiscountCode_Campaign_Id(campaignId)
            .stream()
            .map(SaleTransaction::getSaleAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal budget = campaign.getBudget();
        BigDecimal roiPercentage = BigDecimal.ZERO;

        if (budget.compareTo(BigDecimal.ZERO) > 0) {
            // Formula: ((Revenue - Budget) / Budget) * 100
            roiPercentage = totalRevenue.subtract(budget)
                .divide(budget, 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal("100"));
        }

        RoiReport report = new RoiReport();
        report.setCampaign(campaign);
        report.setTotalRevenue(totalRevenue);
        report.setRoiPercentage(roiPercentage);
        return roiRepository.save(report);
    }
}