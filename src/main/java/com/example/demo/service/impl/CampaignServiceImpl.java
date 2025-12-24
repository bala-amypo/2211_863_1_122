@Service
public class CampaignServiceImpl implements CampaignService {
    private final CampaignRepository campaignRepository;

    // MANDATORY: Constructor Injection for Test Cases
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign createCampaign(Campaign campaign) {
        if (campaign.getBudget().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Budget must be non-negative");
        }
        if (campaign.getStartDate().isAfter(campaign.getEndDate())) {
            throw new IllegalArgumentException("Invalid date range");
        }
        return campaignRepository.save(campaign);
    }
}