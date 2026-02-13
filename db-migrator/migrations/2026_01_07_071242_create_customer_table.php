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
        Schema::create('customer', function (Blueprint $table) {
            $table->unsignedBigInteger('id')->primary()->comment('고유키');
            $table->string('name', 30)->comment('이름');
            $table->string('mobile_no', 20)->comment('휴대전화 번호');
            $table->dateTime('mobile_no_verified_at')->nullable()->comment('인증일시');
            $table->dateTime('retired_at')->nullable()->index()->comment('탈퇴일시');
            $table->boolean('is_agreed_to_terms_and_conditions')->comment('이용약관 동의 여부');
            $table->dateTime('created_at')->index()->comment('생성일시');
            $table->dateTime('deleted_at')->index()->comment('삭제일시');
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('customer');
    }
};
