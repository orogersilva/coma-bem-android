package com.orogersilva.comabem.util;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Assert;
import org.junit.Test;

import static com.orogersilva.comabem.util.NumberUtils.isEven;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by orogersilva on 9/3/2016.
 */
@SmallTest
public class NumberUtilsTest {

    // region TEST METHODS

    @Test
    public void isEven_whenNumberIsEven_returnsTrue() {

        // ARRANGE

        final int EVEN_NUMBER = 2;

        // ACT / ASSERT

        assertTrue(isEven(EVEN_NUMBER));
    }

    @Test
    public void isEven_whenNumberIsNotEven_returnsFalse() {

        // ARRANGE

        final int ODD_NUMBER = 1;

        // ACT / ASSERT

        assertFalse(isEven(ODD_NUMBER));
    }

    @Test
    public void isEven_whenNumberIsNegative() {

        // ASSERT

        final int NEGATIVE_NUMBER = -1;

        // ACT / ASSERT

        assertFalse(isEven(NEGATIVE_NUMBER));
    }

    // endregion
}
