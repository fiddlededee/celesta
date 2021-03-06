package ru.curs.celestaunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import ru.curs.celesta.CallContext;
import s1.HeaderCursor;
import s1.LineCursor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CelestaUnitExtensionNoTruncationNoIntegrityTest {
    public static final String SCORE_PATH = "src/test/resources/score";
    @RegisterExtension
    static CelestaUnitExtension ext =
            CelestaUnitExtension.builder().withScorePath(SCORE_PATH)
                    .withReferentialIntegrity(false)
                    .withTruncateAfterEach(false).build();

    @Test
    void extensionInitializedWithCorrectValues() {
        assertEquals(SCORE_PATH, ext.getScorePath());
        assertFalse(ext.isReferentialIntegrity());
        assertFalse(ext.isTruncateAfterEach());
    }

    @Test
    void integrityCheckDoesNotWork(CallContext ctx) {
        LineCursor lc = new LineCursor(ctx);
        lc.setHeader_id(100);
        lc.setId(100);
        lc.insert();
    }


    @Test
    @DisplayName("When truncateAfterEach is off, tables that are filled in a test...")
    void tablesNotTruncated1(CallContext ctx) {
        CelestaUnitExtensionTest.fillTwoTables(ctx);
    }

    @Test
    @DisplayName("...can be read in the following test.")
    void tablesNotTruncated2(CallContext ctx) {
        HeaderCursor hc = new HeaderCursor(ctx);
        LineCursor lc = new LineCursor(ctx);
        assertEquals(1, hc.count());
        assertEquals(1, lc.count());
    }
}
