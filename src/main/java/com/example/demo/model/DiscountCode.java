@Entity
public class DiscountCode {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String codeValue; 
    private Double discountPercentage;
    @ManyToOne
    private Influencer influencer;
    @ManyToOne
    private Campaign campaign;
    private boolean active;
    // Getters/Setters
}