package nettee.sample.type;

import nettee.sample.builder.TypeSafeMarkers.Missing;
import nettee.sample.builder.TypeSafeMarkers.Present;

import static nettee.enums.EnumUtil.isUniqueAllOf;

public enum SampleStatus {
    PENDING(generateParameters()
            .classifyingBits(0b0000_0001)
            .detailBits(0)
            .canRead(false)),
    ACTIVE(generateParameters()
            .classifyingBits(0b0000_0010)
            .detailBits(0)
            .canRead(true)),
    REMOVED(generateParameters()
            .classifyingBits(0b0000_0100)
            .detailBits(0)
            .canRead(false));
    
    private final int code;

    static {
//        assert Arrays.stream(values())
//                .map(SampleStatus::getCode)
//                .collect(Collectors.toSet())
//                .size() == values().length : "Duplicate code";
        assert isUniqueAllOf(SampleStatus.class, SampleStatus::getCode) : "Duplicate code";
    }
    
//    SampleStatus(boolean canRead, int classifyingBits, int detailBits) {
//        // ... 유효성 생략
//
//        // general purpose readable flag bit: 1
//        // classifying bits: 16
//        // identifying bits(detail bits): 15
//        this.code = (canRead ? 1 << 31 : 0)
//                | (classifyingBits << 15)
//                | detailBits;
//    }

    SampleStatus(SampleStatusParameters<Present, Present> parameters) {
        // ... 유효성 생략
        this.code = (parameters.canRead ? 1 << 31 : 0)
                | (parameters.classifyingBits << 15)
                | parameters.detailBits;
    }

    public static SampleStatusParameters<Missing, Missing> generateParameters() { // builder()
        return new SampleStatusParameters<>();
    }

    public static SampleStatus valueOf(int code) {
        return switch (code) {
            case 0b0_0000_0000_0000_0001__000_0000_0000_0000 -> PENDING;
            case 0b1_0000_0000_0000_0010__000_0000_0000_0000 -> ACTIVE;
            case 0b0_0000_0000_0000_0100__000_0000_0000_0000 -> REMOVED;
            default -> null;
        };
    }

    public int getCode() {  // IntSupplier 또는 Supplier<Integer>
                            // 또는 ToIntFunction<SampleStatus> 또는 Function<SampleStatus, Integer>
        return code;
    }

    // INPUTTED_CLASSIFYING_BITS = Missing
    public static final class SampleStatusParameters<INPUTTED_CLASSIFYING, INPUTTED_DETAIL> {

        private boolean canRead;
        private int classifyingBits;
        private int detailBits;

        private SampleStatusParameters() {}

        public SampleStatusParameters<INPUTTED_CLASSIFYING, INPUTTED_DETAIL> canRead(boolean canRead) {
            this.canRead = canRead;
            return this;
        }

        public SampleStatusParameters<Present, INPUTTED_DETAIL> classifyingBits(int classifyingBits) {
            this.classifyingBits = classifyingBits;
            @SuppressWarnings("unchecked")
            var instance = (SampleStatusParameters<Present, INPUTTED_DETAIL>) this;
            return instance;
        }

        public SampleStatusParameters<INPUTTED_CLASSIFYING, Present> detailBits(int detailBits) {
            this.detailBits = detailBits;
            @SuppressWarnings("unchecked")
            var instance = (SampleStatusParameters<INPUTTED_CLASSIFYING, Present>) this;
            return instance;
        }
    }
}