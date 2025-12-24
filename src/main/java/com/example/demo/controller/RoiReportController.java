@RestController
@RequestMapping("/api/roi")
@Tag(name = "ROI Reports", description = "Operations for generating and viewing ROI")
public class RoiReportController {
    private final RoiService roiService;

    public RoiReportController(RoiService roiService) {
        this.roiService = roiService;
    }

    @PostMapping("/generate/campaign/{id}")
    public ResponseEntity<RoiReport> generateReport(@PathVariable Long id) {
        return ResponseEntity.ok(roiService.generateCampaignRoi(id));
    }
}