@Entity
public class Influencer {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String socialHandle;
    private String name;
    private boolean active;
    // Getters/Setters
}