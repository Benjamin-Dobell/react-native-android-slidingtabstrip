/* @flow */

import React, {
  Component,
} from 'react'
import {
  requireNativeComponent,
  ColorPropType,
  StyleSheet,
  TextInput,
  View,
} from 'react-native'

import PropTypes from 'prop-types'

class SlidingTabStripAndroid extends Component {
  props: {
    children?: any,
    style: any,
  };

  render() {
    const props = {
      ...this.props,
      style: [styles.tabStrip, this.props.style],
    }

    return (
      <RCTSlidingTabStripAndroid {...props} horizontal={true}>
        <View collapsable={false} style={styles.contentContainer} removeClippedSubviews={this.props.removeClippedSubviews}>
          {this.props.children}
        </View>
      </RCTSlidingTabStripAndroid>
    )
  }
}

SlidingTabStripAndroid.propTypes = {
    ...View.propTypes,

    /**
     * Experimental: When true, offscreen child views (whose `overflow` value is
     * `hidden`) are removed from their native backing superview when offscreen.
     * This can improve scrolling performance on long lists. The default value is
     * true.
     */
    removeClippedSubviews: PropTypes.bool,

    /**
     * Sometimes a sliding tab strip takes up more space than its content fills. When this is
     * the case, this prop will fill the rest of the tab strip with a color to avoid setting
     * a background and creating unnecessary overdraw. This is an advanced optimization
     * that is not needed in the general case.
     */
    endFillColor: ColorPropType,

    /**
     * Instead of scrolling exactly to the start of the selected tab, offset the our scroll X
     * by this amount (rounded to the nearest pixel).
     */
    scrollOffset: PropTypes.number,

    /**
     * Height of the selected tab indicator.
     */
    indicatorHeight: PropTypes.number,

    /**
     * Inset the tab dividers from the top and bottom of the view by this amount.
     */
    dividerInset: PropTypes.number,

    /**
     * Width of the tab dividers.
     */
    dividerWidth: PropTypes.number,

    /**
     * Color of the selected tab indicator.
     */
    indicatorColor: ColorPropType,

    /**
     * Color of the tab dividers.
     */
    dividerColor: ColorPropType,
}

const styles = StyleSheet.create({
  tabStrip: {
    flexDirection: 'row',
  },
  contentContainer: {
    flexDirection: 'row',
  },
})

var RCTSlidingTabStripAndroid = requireNativeComponent('RCTSlidingTabStripAndroid', SlidingTabStripAndroid)

export default SlidingTabStripAndroid
