<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('estimation_request', function (Blueprint $table) {
            $table->id()->comment('고유키');
            $table->unsignedBigInteger('customer_id')->index()->comment('고객 고유키');
            $table->unsignedBigInteger('customer_asset_id')->index()->comment('고객 소유 건물 고유키');
            $table->string('title', 100)->comment('의뢰 제목');
            $table->string('subjects', 500)->comment('의뢰 항목들');
            $table->text('description')->comment('의뢰 내용');
            $table->unsignedBigInteger('bid_id')->index()->comment('최종 선택된 견적 고유키');
            $table->dateTime('bid_matched_at')->nullable()->comment('견적 선택일시');
            $table->dateTime('created_at')->index()->comment('생성일시');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('estimation_request');
    }
};
