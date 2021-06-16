package com.mm.kit.common.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.io.ByteArrayInputStream;

/**
 * <pre>
 *     author: momoxiaoming
 *     blog  : http://blog.momoxiaoming.com
 *     time  : 2019/3/23
 *     desc  : new class
 * </pre>
 */
public class LoadingDialogManager
{

    private MyAlertDialog dialog;
    private  String DEFULT_MSG = "正在加载";
    private byte[] loadingImgB64 = new byte[]{-119, 80, 78, 71, 13, 10, 26, 10, 0, 0, 0, 13, 73, 72, 68, 82, 0, 0, 0, 94, 0, 0, 0, 94, 8, 6, 0, 0, 0, -86, -46, -93, 110, 0, 0, 0, 1, 115, 82, 71, 66, 0, -82, -50, 28, -23, 0, 0, 10, -97, 73, 68, 65, 84, 120, 1, -19, 93, 107, 108, 28, -43, 21, -34, 93, -81, -67, 120, 29, -121, -60, 9, 37, 88, -108, 44, -91, 85, -96, -127, 20, 23, 2, 81, 83, 89, -63, 2, 34, 68, 43, -14, 35, 5, 36, 36, 42, -44, 10, -95, 6, -120, 64, 52, 106, 75, 85, -91, 63, 80, 42, 21, 20, -6, -85, 40, -3, 87, -87, -91, -95, 85, -127, -74, 86, 21, 16, 118, 5, -92, -59, 37, 47, -93, -108, -112, 58, -56, 77, -87, 32, 49, -60, -114, -41, 94, 63, -78, -113, -23, -9, -83, 103, 118, -17, -98, -99, -35, -99, -35, -99, 29, 111, 60, -9, 72, -57, 115, -97, -25, -98, -13, -51, -11, -35, -103, -5, 56, 19, 12, 52, 33, 25, -122, 17, -123, 90, -21, 4, -81, 69, -68, 83, 48, -94, -127, 41, -63, -89, 17, 63, -87, 114, 48, 24, -100, 65, -68, -87, 40, -40, 12, -38, 0, -24, 118, -24, -79, 25, -36, 7, -66, 13, 124, 51, 56, 12, 118, -125, 82, 16, 114, 8, 60, 8, 30, 0, 31, -60, -115, -104, -59, -43, -97, 4, -80, 35, -32, -19, -32, 87, -63, 115, 96, -81, -120, 109, -79, 77, -74, 29, -15, 13, -6, 48, -10, 102, -16, 11, -32, 9, -16, 98, 19, 117, -96, 46, -4, 15, -13, -108, 60, 27, 106, 96, 92, 47, 44, 123, 26, 124, -89, 67, 11, 71, 80, -18, 3, 48, -57, 107, 94, 25, -97, 0, -85, 99, 58, -94, 5, -29, -2, 74, -60, -65, 4, -66, 22, -52, -33, 8, 94, 25, 119, 66, -81, -95, -48, 51, 24, -122, -34, 116, 82, -72, -23, -53, 0, -16, 62, -16, 91, -32, 74, 116, 10, 5, -10, -127, -17, 7, 95, -18, -106, 97, -108, 101, -54, -92, 108, -74, 81, -119, -88, 43, 127, 107, 46, 78, -126, -14, -35, -32, -3, 21, -84, 60, -125, -4, -67, -32, 30, -81, -84, 100, 91, 102, -101, 108, -69, 28, 81, -9, 110, -81, -12, -86, -69, 29, 40, 27, 6, 63, 9, -114, -125, 75, -47, 32, 50, -18, 6, -69, -11, -28, 82, -75, -34, 108, -37, -44, 97, 16, -41, 82, 68, 27, 104, -53, -94, -23, -23, -56, 48, 40, 24, 3, 15, -127, 75, 81, 63, 50, -66, -26, 72, -104, -121, -123, -88, 19, -104, -70, -107, -94, 119, -112, 17, -13, 80, 37, -25, 77, 65, -79, 109, -32, 82, 79, 42, -1, 68, -34, 70, -25, -46, 22, -89, 36, 117, 4, 83, 87, 59, -94, 109, -37, 22, 71, 51, -101, 86, -95, 12, -1, 101, -97, -73, -45, 20, 105, -29, -32, 71, -64, 33, -101, -86, 77, -103, 68, 93, 77, -99, -87, -69, 29, -47, -42, -59, 29, 122, -96, 64, 59, -8, -49, 118, -38, 33, -19, 101, -16, 101, 77, -119, -82, 3, -91, -88, -69, 105, 3, 46, 69, 68, -101, -7, -74, -19, 61, -95, -31, -107, -32, -125, 69, 42, 25, -58, 60, -46, 118, 122, -81, 81, 99, 90, -92, 45, 96, -38, 36, -119, -74, -13, -67, -63, 59, 66, -125, 124, 84, 60, 46, 53, 65, 124, 20, -20, -7, 91, 96, -93, 45, -89, 77, -90, 109, -72, 24, -58, -40, 92, -38, -40, 53, -100, 48, 118, 28, 73, -116, -3, 108, 100, 126, 125, -93, -37, -49, -54, 71, -69, -20, -23, 118, -96, 31, 65, -70, 107, 47, 62, -98, 24, 83, 69, 35, -76, 13, 76, 27, -115, -34, -127, -72, 17, 120, 105, 60, -53, -41, -12, -97, -97, 123, 106, 120, -10, -22, 42, 68, 101, -117, 86, -11, -93, -121, 54, 57, -82, -3, 5, 44, -17, 50, 103, -2, -74, -32, 117, -5, 108, 86, -22, 18, -4, 99, -38, -74, 5, -90, 13, 30, -98, -32, -124, -25, 2, 125, -104, -56, 68, 94, 63, 123, -31, -40, 15, -34, -85, 110, -40, 113, 12, 60, 64, -25, 47, -7, 75, 96, -7, 28, -2, 10, -46, -18, -126, 98, -15, 5, 85, -106, -18, 95, -45, -58, -69, -74, 94, -47, 54, -86, 90, 57, 124, 62, -67, -4, -19, -49, -90, -34, -33, -3, 47, -93, 77, 77, 47, 23, 118, 12, 60, -124, 60, 11, -2, -122, 16, -58, -98, 126, 63, 20, -102, 23, -23, 75, 54, 74, 91, 55, 92, -38, -79, 97, 99, 87, 11, 39, -20, 114, -12, -10, 103, -87, 53, -17, -114, 79, 15, -27, 18, 42, 4, 28, 1, -113, -34, -50, 23, 7, -7, -92, 114, 20, 105, -37, -4, 4, -70, -123, -27, -18, -11, -63, -23, 59, 87, -73, -34, 112, 109, 103, -88, 96, 101, -85, -1, -109, -28, -115, 15, 31, -102, -39, 99, -107, 43, 119, -83, 56, 45, 12, -48, 99, 16, 64, -112, 87, 40, -126, -2, -125, -16, 38, -128, -66, 100, -57, 116, -59, -42, -110, -63, -89, -122, -25, -81, -1, -35, 71, 115, 71, -2, 55, -109, 110, -75, 10, 93, -38, 26, -54, 124, 47, -42, -42, -69, -89, 39, 122, -48, 74, -77, -69, -106, -19, -15, -26, -72, -66, 31, 21, 85, -48, 47, 32, -2, 45, -65, -125, 78, 48, -97, -3, 74, -28, -8, 125, 87, -75, 61, -44, 26, 10, 26, -116, -109, 38, -109, -103, 80, -1, 88, -14, -81, -113, -115, -108, 95, -35, 42, 11, 60, -28, 60, 14, -66, -123, 2, 21, -38, 5, -48, -71, -122, -87, 9, 8, 60, -73, -95, -3, 55, -9, 94, -39, -10, -78, 10, -58, 123, -109, -23, -50, -79, 79, 19, 127, 80, -45, 28, -121, -47, -37, -7, -110, 36, -89, 118, 11, 26, 112, 44, -52, 7, 5, 55, 15, -60, 63, -74, -98, -19, 121, -19, -8, -29, 68, -26, -5, -61, -119, -81, -106, 50, -67, 92, -113, -33, -117, 74, -36, 78, 97, -47, 4, 2, 15, 91, 17, 125, 45, 68, -32, -114, 43, -38, -74, -84, 108, 11, 101, -84, -44, 68, -54, 8, 30, -98, -56, -108, -20, -88, -74, -64, -93, -89, -9, 65, -64, -67, -106, 16, -13, -6, 35, 12, 49, -97, -118, 52, 29, 53, 17, -40, 125, -35, 37, -1, -66, -89, 59, -4, -94, 10, -56, -64, 88, -14, -86, -99, -57, 102, -98, 80, -45, -84, -80, 45, -16, -56, -4, -87, 85, -64, -68, -66, -117, -21, 62, -111, -90, -93, 2, -127, -60, -58, -114, 111, -81, 95, 30, 74, -88, -55, 111, -100, 77, -18, 86, -29, 86, -72, 8, 120, -12, 118, -18, 6, -8, -70, 85, -64, -68, -18, 64, 111, -49, -3, 27, -119, 60, 29, 53, 17, -8, 125, 48, -104, -18, -69, 60, -4, -88, -6, -116, 126, 124, 50, -67, -4, -47, 35, 51, -113, 85, 4, 9, -64, 31, 0, -85, -44, 95, -79, -110, 46, 80, -128, 0, 126, 104, -49, -88, 63, -76, -73, -66, 17, 47, 26, -94, 11, 122, 60, -48, -26, -108, -82, -36, -9, -14, 76, -127, 84, 29, -87, -120, -64, 45, 93, 45, 63, 81, 11, 13, -99, 75, -83, -34, 117, 116, -6, 1, 53, -83, 0, 120, 100, 124, 87, -51, 68, -8, 111, 24, 98, -2, 46, -46, 116, -76, 2, 2, 123, 111, -20, -40, -41, -77, -78, -27, -68, 90, -20, 88, 60, -13, 99, 53, -98, 3, 30, -67, -99, -5, 8, -17, 83, 51, 17, -26, -60, -104, -90, 26, 16, -72, -75, 43, -4, -68, 90, -19, -99, -15, -12, -70, 93, 31, 24, -71, -57, -13, 28, -16, 40, -12, 77, -80, 58, 53, -64, 121, -104, 3, 106, 101, 29, 118, -114, -64, -102, 75, -94, 123, 46, -117, -124, -46, 86, -115, 120, -46, 8, 78, 78, -49, -26, -122, 32, 21, -8, 7, -83, 66, -26, -11, 69, 12, 51, -7, 25, 127, -111, -87, -93, -27, 17, -64, 12, -26, -123, 77, -85, -61, -61, 106, -87, 19, 83, -87, -36, 56, -97, 5, 30, -61, 76, 59, 10, 108, 85, 11, 33, -4, 107, 17, -41, -47, 42, 17, -8, 66, 52, -16, -100, 90, -27, 31, -25, -46, 107, 118, 12, -59, 87, 49, -51, -22, -15, -101, 17, 86, 87, 79, 62, 68, 111, 63, -86, 86, -46, -31, -22, 17, -8, 69, -49, -78, -33, -58, 58, 66, 73, -85, 102, 50, 99, 4, -115, 112, -8, 59, -116, 91, -64, -9, 89, -103, -26, 117, 64, -60, 117, -76, 70, 4, -66, -36, -39, 114, 74, -83, 122, 102, 46, 125, 15, -29, 22, -16, -73, -87, -103, 8, 107, -32, 5, 32, -75, 70, -69, -93, -63, -41, -43, -70, -89, -90, 51, 55, 48, -50, -19, 106, 60, -24, 37, -9, -62, 112, 45, 85, -109, 11, 8, -84, 104, 109, -3, -107, 42, -26, -3, -87, -52, -78, 31, -98, -120, -81, 98, -113, 95, 7, 86, -9, 2, -114, 96, 124, -9, -11, -110, -98, 10, 84, -67, 97, -82, 82, -59, -94, 45, 92, -75, -53, 82, 10, -29, -4, -52, 92, 104, -85, 5, -68, -107, -50, 43, -113, -67, 104, 114, 17, -127, -49, 71, -125, -29, -86, -72, 68, 42, -72, 73, 3, -81, 34, -46, -96, -16, -86, 72, -24, -65, -86, -24, -55, -108, 113, -67, 29, -16, 60, -20, -91, -55, 69, 4, 86, -124, 3, 39, 84, 113, -25, -26, 51, 87, 19, -8, -75, 106, 34, -62, 35, 34, -82, -93, 117, 34, -80, -84, 53, 116, 88, 21, 113, -18, -126, -47, 69, -32, 59, -43, 68, -124, -71, -74, -86, -55, 69, 4, -94, 97, -29, 35, 85, 92, 34, 21, 104, -77, 3, -98, -25, 72, 53, -71, -120, 64, 75, -70, 112, -83, 122, 54, 109, -124, 53, -16, 46, 2, 92, 74, 84, 75, 56, 116, 70, -51, -61, 14, -124, -112, 6, 94, 69, -92, 65, -31, -71, 76, -92, 16, -8, 116, 32, 72, -32, 53, 53, 24, -127, -97, 111, 8, 36, -81, 108, -49, 47, -127, 127, 46, 18, -52, -50, -43, -56, 49, 93, -2, -40, 54, 88, 45, 95, -120, -17, -36, 119, 83, 71, -128, -32, -109, 127, 121, 83, 116, 42, -128, -71, -102, 81, -80, 74, 49, 95, 64, -31, -95, -111, 0, -105, 7, -81, 85, 26, -27, 80, -93, 123, 124, -29, 111, -126, 28, 69, -90, -20, -128, -9, -10, 8, 97, -29, -115, 110, -122, 22, -70, -124, 18, 89, -32, 79, -117, 68, -89, -2, 93, 68, 53, 29, 45, -125, -64, 23, 69, -34, 105, -10, 120, 57, 55, -61, 105, 98, 77, -18, 34, 64, -121, 69, 42, -99, -76, 3, 94, 22, 82, 43, -24, 112, 109, 8, -56, -50, -84, -127, -81, 13, -57, -86, 107, -55, -50, 124, -110, -113, -109, 81, 112, 82, 125, -42, 65, 120, -55, -98, -48, -82, 26, -78, 58, 43, 16, 75, -127, 45, -79, -114, -122, -80, -52, -57, 35, -125, -14, 76, -109, 92, -4, -82, -77, 121, 95, 87, -105, 88, 30, 34, -26, 28, -29, 73, 114, 113, -69, 111, 33, 89, -1, 117, 1, 1, -119, 101, 30, 107, 116, -3, -37, -59, -65, 67, -63, 94, 16, 23, 26, -9, -83, 8, -32, 42, 61, -1, -35, -98, 3, 3, -103, 116, -8, 67, 15, -92, 42, -11, -28, 10, -24, 64, 77, 8, 0, 76, 122, -4, 83, -119, 24, 115, -69, -28, -62, -122, 38, -116, 57, -77, 8, -53, -99, -63, 114, 19, 107, 77, -115, -5, -68, -110, -60, -16, -128, -119, 117, 30, 22, -36, -119, -19, -22, -83, 65, -104, 126, 25, -43, -3, 54, -7, -62, 58, 84, 17, 1, 98, 103, 98, -120, 75, -114, -74, 23, 85, 68, 22, -99, 44, 75, 47, 122, 119, 23, 21, -44, 9, -114, 16, 0, -106, -12, -85, -87, 18, -79, -115, -40, 86, 70, 6, 29, 28, -85, -108, -1, 5, -74, -83, -95, 19, 75, 33, 0, 16, 7, 85, 32, 17, 126, -95, 84, 89, -66, 76, -47, -9, -106, 36, -23, 24, -88, 100, 125, -99, -79, -128, 0, 0, -92, 3, 81, 73, 114, 127, 106, 33, 92, 40, -83, -113, 91, 22, 66, 82, 117, 12, 24, 74, -81, -83, -14, -63, -91, 88, 38, 42, -11, -54, 91, -123, 120, -45, 123, 73, 45, -74, 100, 113, 82, -120, -107, 13, 126, -67, -114, -76, 65, 69, -23, 118, -100, 110, 95, -83, -73, 92, 71, 50, -4, 88, -120, 24, -127, -91, -117, -36, -73, 28, 99, -127, -54, -12, -7, 46, -23, 17, -57, 2, 124, 90, 16, -128, -47, -99, -81, 36, 57, 101, 80, 30, 29, -44, -90, -1, 116, -107, -24, 107, -9, -94, 117, 89, 91, -34, -38, -6, 115, -119, 13, 88, -6, 35, -34, 95, -75, 100, 8, -47, -114, -126, -86, 64, 13, 120, -47, -105, -78, 74, 116, -78, -44, 93, -123, -120, 124, 81, 84, -92, -45, 122, 73, 59, -13, 37, 116, -120, 8, 0, 32, -6, 31, -106, -12, 100, -51, -24, 64, 18, 95, 123, -23, -76, 94, 37, 58, 56, 46, -1, 76, 90, 115, -117, 23, 95, 69, 98, 1, -106, 78, -97, -119, 89, 125, -45, 45, 16, -64, -51, 56, 114, 42, 97, 20, 105, -66, 95, -91, 34, 6, 96, 98, -95, 18, -79, -118, -71, -46, -123, 32, -120, 95, 67, -112, 68, 7, -57, -53, 93, 105, -32, 34, 20, 66, -37, -63, -60, 64, -46, 54, 87, -51, -127, 116, -69, -81, 34, 12, 32, -35, 126, -30, -57, -43, -42, -101, 75, 24, 109, 6, -45, 118, 73, 5, 30, 59, 92, -47, 26, 45, 112, -68, -73, -5, 58, 2, 127, -51, 125, 3, 62, 109, 5, -45, 102, 73, -60, -90, -66, 113, -67, -44, -99, -126, 96, -82, 84, -39, 125, 37, -127, 119, 127, -55, 15, 59, -76, 17, 108, -41, -45, -119, 73, 118, 101, -87, 20, 118, 117, -89, -93, 1, -33, 59, -18, 7, 6, 42, -15, 35, 6, -34, -20, 55, 69, 67, -66, -2, 84, -123, -126, 58, 65, -81, -19, 37, -87, -42, 127, 1, -34, 101, -80, -35, -80, -61, 103, -38, 37, -13, -110, 69, 91, -64, -14, 57, 29, 73, 89, -37, -67, -23, -23, -14, 38, -95, 113, -3, 57, 34, 9, -118, 87, 113, -128, -49, -89, 29, -69, 71, 77, 36, -21, 15, 112, 53, -4, 62, 0, 100, -66, 100, -15, -83, -51, -114, 56, 71, -35, -12, -117, 41, -44, 17, 44, -25, -45, 45, 123, 104, -101, -69, 47, 71, 110, -35, 21, 40, -58, -23, -123, 33, 75, 83, -101, 43, -105, -60, -102, 110, 13, -105, 58, -127, -87, 91, 41, -94, 77, 49, -73, 112, 106, -120, 28, 40, -56, -95, -121, -77, -102, -46, -9, 60, -110, 114, 52, -120, -112, -2, -84, 104, 35, -18, 0, -128, -27, 35, -25, 126, 112, 57, -30, -90, -87, -67, 96, -49, -74, 11, -78, 45, -77, 77, -74, 93, -114, -88, -69, -73, -113, -118, 110, -34, 8, 40, -49, 101, 68, -71, -122, 107, 103, -80, -81, 62, 29, -99, 63, 110, -20, 38, -38, 54, -78, -128, 52, 87, -38, -97, 6, 75, -89, -47, 54, -91, -77, 73, 116, -33, 66, 111, 81, 100, -98, -45, 98, -100, -98, 69, 120, 60, -44, 98, 4, 109, 63, -106, -50, -93, 47, 60, -123, 65, 118, 122, -104, -18, 53, -108, 93, 58, 31, 75, -121, 49, 5, -124, 27, -64, -123, 3, -18, 88, 43, -11, 4, -124, 44, -49, -120, 58, 80, 23, -1, 44, -20, -64, 88, -50, -14, 109, 7, -1, 9, -52, -19, -53, 94, 17, -37, 98, -101, 108, 123, -47, 102, 85, 61, 27, 106, 10, -70, -67, -120, 0, 0, -50, -20, 109, 6, 115, 43, 4, -113, -82, -80, 7, -70, 53, -59, 74, -1, -56, 60, 106, -60, 125, -96, 3, -32, -125, 69, 91, -91, -111, -24, 53, 53, 5, -16, -46, 104, -36, 8, -6, -62, -28, 56, -83, -14, 90, -60, 121, 52, 93, 101, 68, 115, -29, -67, 53, -18, -97, 70, 26, 127, 19, 114, -52, 51, 71, 44, -40, 76, -12, 127, -1, 71, -102, -115, 48, -19, -41, 70, 0, 0, 0, 0, 73, 69, 78, 68, -82, 66, 96, -126};

    private Activity activity;
    private String msg;
    private long h = -1L;
    private final int i = 1;
    private final long j = 15000L;
    private boolean k = false;
    private Handler l = new Handler(Looper.getMainLooper());

    public LoadingDialogManager(Activity activity)
    {
        this.activity = activity;
    }

    public LoadingDialogManager(Activity activity, String msg)
    {
        this.activity = activity;
        this.msg = msg;
    }

    public void a(boolean var1)
    {
        this.k = var1;
    }

    public void setDialogMsg(String var1)
    {
        this.msg = var1;
    }


    public void show()
    {
        if (activity != null)
        {
            activity.runOnUiThread(new ShowRunnable(this));
        }

    }

    public void dismiss()
    {
        if (activity != null)
        {
            activity.runOnUiThread(new DismissRunnable(this));
        }

    }

    public class MyAlertDialog extends AlertDialog
    {
        private MyAlertDialog(Context var2)
        {
            super(var2);
        }

        protected void onCreate(Bundle var1)
        {
            super.onCreate(var1);
            this.setContentView(this.displayMetrics(this.getContext()));
            Window var2 = this.getWindow();
            if (var2 != null)
            {
                var2.setBackgroundDrawable(new ColorDrawable(0));
            }

        }

        private View displayMetrics(Context var1)
        {
            LinearLayout var2 = new LinearLayout(var1);
            FrameLayout.LayoutParams var3 = new FrameLayout.LayoutParams(-2, this.displayMetrics(var1, 50.0F));
            var3.gravity = 17;
            var2.setLayoutParams(var3);
            GradientDrawable var4 = new GradientDrawable();
            var4.setColor(-450944201);
            var4.setCornerRadius((float) this.displayMetrics(var1, 5.0F));
            var2.setBackgroundDrawable(var4);
            ImageView var5 = new ImageView(var1);
            LinearLayout.LayoutParams var6 = new LinearLayout.LayoutParams(this.displayMetrics(var1, 20.0F), this.displayMetrics(var1, 20.0F));
            var6.gravity = 16;
            var6.setMargins(this.displayMetrics(LoadingDialogManager.this.activity, 17.0F), this.displayMetrics(activity, 10.0F), this.displayMetrics(activity, 8.0F), this.displayMetrics(activity, 10.0F));
            var5.setLayoutParams(var6);
            var5.setScaleType(ImageView.ScaleType.FIT_CENTER);
            var5.setImageDrawable(this.b64StrToDrawable(var1, loadingImgB64));
            RotateAnimation var7 = new RotateAnimation(0.0F, 359.0F, 1, 0.5F, 1, 0.5F);
            var7.setRepeatCount(-1);
            var7.setDuration(900L);
            var7.setInterpolator(new LinearInterpolator());
            var5.startAnimation(var7);
            TextView var8 = new TextView(var1);
            var8.setText(msg==null ? DEFULT_MSG : msg);
            var8.setTextSize(16.0F);
            var8.setTextColor(-1);
            LinearLayout.LayoutParams var9 = new LinearLayout.LayoutParams(-2, -2);
            var9.gravity = 16;
            var9.setMargins(0, 0, this.displayMetrics(var1, 17.0F), 0);
            var8.setLayoutParams(var9);
            var2.addView(var5);
            var2.addView(var8);
            FrameLayout var10 = new FrameLayout(var1);
            FrameLayout.LayoutParams var11 = new FrameLayout.LayoutParams(-2, -2, 17);
            var10.setLayoutParams(var11);
            var10.addView(var2);
            return var10;
        }

        private Drawable b64StrToDrawable(Context var1, byte[] var2)
        {
            ByteArrayInputStream var4 = null;
            BitmapDrawable var5 = null;

            try
            {
                var4 = new ByteArrayInputStream(var2);
                BitmapFactory.Options var6 = new BitmapFactory.Options();
                var6.inDensity = 480;
                var6.inTargetDensity = var1.getResources().getDisplayMetrics().densityDpi;
                Bitmap var7 = BitmapFactory.decodeStream(var4, null, var6);
                var5 = new BitmapDrawable(var1.getResources(), var7);
            } catch (Throwable e)
            {
                e.printStackTrace();
            } finally
            {
                if (var4 != null)
                {
                    try
                    {
                        var4.close();
                    } catch (Exception var15)
                    {
                        var15.printStackTrace();
                        ;
                    }
                }

            }

            return var5;
        }

        private int displayMetrics(Context var1, float var2)
        {
            Resources var3 = var1 == null ? Resources.getSystem() : var1.getResources();
            float var4 = var3.getDisplayMetrics().density;
            return (int) (var2 * var4);
        }
    }


    class ShowRunnable implements Runnable
    {
        private LoadingDialogManager loadingDialogManager;

        public ShowRunnable(LoadingDialogManager loadingDialogManager)
        {
            this.loadingDialogManager = loadingDialogManager;
        }

        @Override
        public void run()
        {
            if (loadingDialogManager.dialog == null)
            {
                loadingDialogManager.dialog = new MyAlertDialog(loadingDialogManager.activity);
                loadingDialogManager.dialog.setCancelable(true);
            }

            if (!loadingDialogManager.dialog.isShowing())
            {
                loadingDialogManager.dialog.show();
            }
        }
    }

    class DismissRunnable implements Runnable
    {
        private LoadingDialogManager loadingDialogManager;

        public DismissRunnable(LoadingDialogManager loadingDialogManager)
        {
            this.loadingDialogManager = loadingDialogManager;
        }

        @Override
        public void run()
        {
            if (loadingDialogManager.dialog != null && loadingDialogManager.dialog.isShowing())
            {
                loadingDialogManager.dialog.dismiss();
            }


        }
    }
}
