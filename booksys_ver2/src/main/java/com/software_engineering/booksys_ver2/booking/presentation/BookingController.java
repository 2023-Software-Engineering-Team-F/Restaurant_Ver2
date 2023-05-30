package com.software_engineering.booksys_ver2.booking.presentation;

import com.software_engineering.booksys_ver2.booking.application.BookingService;
import com.software_engineering.booksys_ver2.booking.domain.Booking;
import com.software_engineering.booksys_ver2.booking.presentation.dto.request.CreateReservationRequest;
import com.software_engineering.booksys_ver2.booking.presentation.dto.request.CreateWalkInRequest;
import com.software_engineering.booksys_ver2.booking.presentation.dto.request.UpdateBookingRequest;
import com.software_engineering.booksys_ver2.booking.presentation.dto.response.BookingListResponse;
import com.software_engineering.booksys_ver2.booking.presentation.dto.response.BookingResponse;
import com.software_engineering.booksys_ver2.customer.application.CustomerService;
import com.software_engineering.booksys_ver2.table.application.TableService;
import com.software_engineering.booksys_ver2.table.domain.Table;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Api(tags = "예약 관련 API")
public class BookingController {

  private final BookingService bookingService;
  private final CustomerService customerService;
  private final TableService tableService;

  /**
   * 온라인 예약 생성
   */
  @PostMapping("/reservation")
  @ApiOperation(value = "온라인 예약 API", notes = "")
  public ResponseEntity<Long> createReservation(@RequestBody CreateReservationRequest request) {

    Long customerId = customerService.createCustomer(request.getName(), request.getPhoneNumber());
    Table table = tableService.findByTableNumber(request.getTableNumber());
    Long tableId = table.getId();

    Long reservationId = bookingService.createReservation(customerId, 1L, tableId, request.getBookingDateTime(), request.getCovers());

    return ResponseEntity.ok(reservationId);
  }

  /**
   * 현장 에약 생성
   */
  @PostMapping("/walkIn")
  @ApiOperation(value = "현장 예약 API", notes = "")
  public ResponseEntity<Long> createWalkIn(@RequestBody CreateWalkInRequest request) {

    Long customerId = customerService.createCustomer(request.getName(), request.getPhoneNumber());
    Table table = tableService.findByTableNumber(request.getTableNumber());
    Long tableId = table.getId();

    Long walkInId = bookingService.createWalkIn(customerId, 1L, tableId, request.getCovers());

    return ResponseEntity.ok(walkInId);
  }

  /**
   * 개별 예약 조회
   */
  @GetMapping("/booking/{bookingId}")
  @ApiOperation(value = "개별 예약 조회 API", notes = "")
  public BookingResponse getBooking(@PathVariable Long bookingId) {

    Booking booking = bookingService.findById(bookingId);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    return new BookingResponse(booking.getId(), booking.getCustomer().getName(),
        booking.getCustomer().getPhoneNumber(), booking.getTable().getTableNumber(),
        booking.getCovers(), booking.getBookingStatus(),
        booking.getBookingDateTime().format(formatter), booking.getArrivalDateTime().format(formatter));
  }

  /**
   * 전체 예약 조회
   */
  @GetMapping("/booking")
  @ApiOperation(value = "전체 예약 조회 API", notes = "")
  public BookingListResponse<List<BookingResponse>> getBookingList() {

    List<Booking> bookingList = bookingService.findAll();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    List<BookingResponse> bookingListResponse =
        bookingList.stream().map(booking -> new BookingResponse(
            booking.getId(), booking.getCustomer().getName(),
            booking.getCustomer().getPhoneNumber(), booking.getTable().getTableNumber(),
            booking.getCovers(), booking.getBookingStatus(),
            booking.getBookingDateTime().format(formatter), booking.getArrivalDateTime().format(formatter)
        )).collect(Collectors.toList());

    return new BookingListResponse<>(bookingListResponse.size(), bookingListResponse);
  }

  /**
   * 예약 변경
   */
  @PatchMapping("/booking/{bookingId}")
  @ApiOperation(value = "예약 변경 API", notes = "")
  public ResponseEntity<Void> updateBooking(@PathVariable Long bookingId, @RequestBody UpdateBookingRequest request) {

    Booking booking = bookingService.findById(bookingId);
    bookingService.updateBooking(bookingId, request.getTableNumber(),
        request.getCovers(), request.getBookingDateTime());

    return ResponseEntity.noContent().build();
  }

  /**
   * 예약 취소(삭제)
   */
  @DeleteMapping("/booking/{bookingId}")
  @ApiOperation(value = "예약 취소 API", notes = "")
  public ResponseEntity<Void> deleteBooking(@PathVariable Long bookingId) {

    bookingService.deleteBooking(bookingId);

    return ResponseEntity.noContent().build();
  }
}
