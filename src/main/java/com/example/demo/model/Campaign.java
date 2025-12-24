@Entity
public class Campaign {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private BigDecimal budget; // Must be non-negative
    private LocalDate startDate;
    private LocalDate endDate;
    // Getters/Setters
}